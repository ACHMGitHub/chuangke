package cn.ck.controller.studio;

import cn.ck.controller.AbstractController;
import cn.ck.controller.FileController;
import cn.ck.entity.*;
import cn.ck.entity.bean.ProjectBid;
import cn.ck.service.*;
import cn.ck.utils.ConstCofig;
import cn.ck.utils.DateUtils;
import cn.ck.utils.ResponseBo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author 马圳彬
 * @version 1.0
 * @description 创客
 * @date 2018/10/4 7:29
 **/
@Controller
@RequestMapping("/stuproject")
public class StuprojController extends AbstractController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BiddingService biddingService;
    @Autowired
    private StudioService studioService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PromulgatorService promulgatorService;


    /*竞标项目*/
    @GetMapping("/projectbid")
    @ResponseBody
    public ResponseBo bidproject(){
        PageHelper.startPage(1,1);
        String userId = getUser().getAllId();
        Users user = usersService.selectOne(new EntityWrapper<Users>().eq("user_id",userId)) ;
        String stuId = user.getUserStudio();

        List<Bidding> bidList = biddingService.selectbidding1(stuId);
        /*System.out.println("工作室ID："+stuId);*/
        List<ProjectBid> projbidList = new ArrayList<ProjectBid>();
       /* System.out.println(bidList);*/
        for(Bidding bidding:bidList){
            int projId = bidding.getBidProj();
            Project project = projectService.selectOne(new EntityWrapper<Project>().eq("proj_id",projId));

            ProjectBid projectBid = new ProjectBid();
            projectBid.setProject(project);
            projectBid.setBidding(bidding);

            //转换时间格式
            String creatdate= DateUtils.format(project.getProjCreattime(),DateUtils.DATE_PATTERN);
            projectBid.setCreatdate(creatdate);
            //竞标剩余时间
            projectBid.setBidday(10-projectService.projBidTimeNum(project.getProjId()));
            int bidday = projectBid.getBidday();

            //竞标人数
            int count=biddingService.selectCount(new EntityWrapper<Bidding>().eq("bid_proj",bidding.getBidProj()));
            projectBid.setBidnum(count);
            if(bidday<=0){
                bidding.setBidState("竞标超时");
                biddingService.updateAllColumnById(bidding);
            }


            projbidList.add(projectBid);
        }
        PageInfo<ProjectBid> page=new PageInfo<>(projbidList);
        return ResponseBo.ok().put("page",page).put("user",user);
    }

    /* 服务订单 */
    @GetMapping("/service")
    @ResponseBody
    public ResponseBo service(){
        //工作室ID
        String userId = getUser().getAllId();
        Users user = usersService.selectOne(new EntityWrapper<Users>().eq("user_id",userId)) ;
        String stuId = user.getUserStudio();
        /*进行中的项目*/
        List<Project> projList1 = projectService.selectProjlist1(stuId);
        List<ProjectBid> projbidList = new ArrayList<ProjectBid>();

        for(Project project:projList1){

            ProjectBid projBid = new ProjectBid();
             /*开发剩余时间 */
           /* int projId = bidding.getBidProj();
            Project project = projectService.selectOne(new EntityWrapper<Project>().eq("proj_id",projId));
            System.out.println(projId+"|"+project.getProjState());*/

            int developday = project.getProjCycletime() - projectService.projDevelopTimeNum(project.getProjId());
            System.out.println();
            projBid.setDevelopday(developday);
            /*projBid.setBidding(bidding);*/
            projBid.setProject(project);
                projbidList.add(projBid);
        }

        /*已结束*/

        List<Project> projectList2 = projectService.selectProjlist2(stuId);
        List<ProjectBid> projbidList1 = new ArrayList<ProjectBid>();
        for(Project project:projectList2){
/*            int projId = bidding.getBidProj();
            Project project = projectService.selectOne(new EntityWrapper<Project>().eq("proj_id",projId));*/

            ProjectBid projBid = new ProjectBid();
            /*projBid.setBidding(bidding);*/
            projBid.setProject(project);
            projbidList1.add(projBid);
        }

        return ResponseBo.ok().put("projectrun",projbidList).put("projectend",projbidList1).put("user",user);
    }

    /*竞标一览*/
    @GetMapping("/biddingAll")
    @ResponseBody
    public ResponseBo biddingAll(){
        //工作室ID
        String userId = getUser().getAllId();
        Users user = usersService.selectOne(new EntityWrapper<Users>().eq("user_id",userId));
        String stuId = user.getUserStudio();

        List<Bidding> bidList2 = biddingService.selectbidding3(stuId);
        List<ProjectBid> projBid2 = new ArrayList<>();
        for(Bidding bidding:bidList2){
            int projId = bidding.getBidProj();
            Project project = projectService.selectOne(new EntityWrapper<Project>().eq("proj_id",projId));
            String userId1 = project.getProjProm();
            System.out.println(userId1);
            Users user1 = usersService.selectOne(new EntityWrapper<Users>().eq("user_id",userId1));
            System.out.println(user1);

            ProjectBid projbid = new ProjectBid();
            projbid.setBidding(bidding);
            projbid.setProject(project);
            projbid.setUsers(user1);
            //竞标个数
            int count=biddingService.selectCount(new EntityWrapper<Bidding>().eq("bid_proj",project.getProjId()));
            projbid.setBidnum(count);

            projBid2.add(projbid);
        }
        return ResponseBo.ok().put("projectbid",projBid2).put("user",user);
    }

    @GetMapping("/bpmanager/{id}")
    @ResponseBody
    public ResponseBo bpManager(@PathVariable("id") int id){
        /* id为 竞标ID */
        Bidding bidding = biddingService.selectOne(new EntityWrapper<Bidding>().eq("bid_id",id));
        int projId = bidding.getBidProj();

        Project project = projectService.selectOne(new EntityWrapper<Project>().eq("proj_id",projId));
        String yhid = project.getProjProm();

        Promulgator prom = promulgatorService.selectOne(new EntityWrapper<Promulgator>().eq("prom_id",yhid));

        return ResponseBo.ok().put("bidding",bidding).put("project",project).put("promulgator",prom);
    }

    @RequestMapping("/bidinfoAdd")
    /*@ResponseBody*/
    public String bidinfoAdd(HttpServletRequest request){
        String bidId = request.getParameter("bidId");
        Bidding bidding = biddingService.selectOne(new EntityWrapper<Bidding>().eq("bid_id",bidId));
        bidding.setBidMoney(request.getParameter("bidMoney"));
        bidding.setBidCycle(request.getParameter("bidCycle"));
        bidding.setBidScheme(request.getParameter("bidScheme"));
        bidding.setBidPhone(request.getParameter("bidPhone"));
        bidding.setBidEmail(request.getParameter("bidEmail"));

        System.out.println(bidding);
        System.out.println("跳转成功");
        biddingService.updateAllColumnById(bidding);

        return "redirect:/studioPage/bpManager?bidId="+bidId;
    }

    @PostMapping("/biddingEnd/{id}")
    @ResponseBody
    public boolean biddingEnd(@PathVariable("id") int id){

        System.out.println(id);
        Bidding bidding = biddingService.selectOne(new EntityWrapper<Bidding>().eq("bid_id",id));
        bidding.setBidEndtime(new Date());
        bidding.setBidState("竞标中止");

        biddingService.updateAllColumnById(bidding);
        return true;
    }

    /*置 中止合作 为 承接方中止*/
    @PostMapping("/userQuit/{id}")
    @ResponseBody
    public boolean userQuit(@PathVariable("id") int id){

        System.out.println(id);
        Project project = projectService.selectOne(new EntityWrapper<Project>().eq("proj_id",id));
        project.setProjState("承接方中止");
        projectService.updateAllColumnById(project);
        return true;
    }

    /*点击 取消中止，置 承接方中止 和 发布方中止 为 开发中*/
    @PostMapping("/develop/{id}")
    @ResponseBody
    public boolean develop(@PathVariable("id") int id){

        System.out.println(id);
        Project project = projectService.selectOne(new EntityWrapper<Project>().eq("proj_id",id));
        project.setProjState("开发中");
        projectService.updateAllColumnById(project);
        return true;
    }

    /*点击确认中止，置 发布方中止 为 项目中止*/
    @PostMapping("/projectQuit/{id}")
    @ResponseBody
    public boolean projectQuit(@PathVariable("id") int id){

        System.out.println(id);
        Project project = projectService.selectOne(new EntityWrapper<Project>().eq("proj_id",id));
        project.setProjState("项目中止");
        project.setProjEndtime(new Date());
        projectService.updateAllColumnById(project);
        return true;
    }

/*    //获取项目头像
    @RequestMapping("/showImg/{fileName}")
    public void showPicture(@PathVariable("fileName") String fileName, HttpServletResponse response){
        //将文件名分割成 文件名 和 格式
        //“ . " 需要用两次转义

        //获取服务器中的文件
        File imgFile = new File(ConstCofig.RootPath + ConstCofig.projectimgPath + fileName);
        //输出到页面
        FileController.responseFile(response, imgFile);
    }*/

}
