package cn.ck.utils;

public class AlipayConfig {

	public static String app_id = "2016092000558572";//�ں�̨��ȡ���������ã�
	
	public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQMX+xFj1kJTeSYE69EC5xTdnhDkVAPex+DfSLcvh8+t5XWMvvFG5y+sEsQj4tXV+3Xq2fCCot2u7YBSXUTSDQopy6lqqhy8Iuhwp0WAdqvzBL5i9Zmyus5Rhii9yW9Z2eMKC2E9CDJUGwLDwOOKj9PDcEgD5oOSLUEccUpo8GI7MsQ7dpe+jh7hdvaUG4dHUweQpNV044Sx+jYzwh1ZQVgfHNaRK5jBhl2oL28FMOf/UVyExO75NuuHZzuktkOgG5mQJDYdpPS+O6IeLlmb8nNy+sPAjz2nslWgvp3faMBF28uLuoJYm7U4/PtZ0XZObE3zd7olLQvoevsJa06GoNAgMBAAECggEABxmY2wRoU3UNrnR1jBOu5b9amh1UxZwG+BIB15f6qlTD+/MQX55W7exw9JfybdNqMiJEVE9XnFhqaFKgkTqXAg7vvrRKhJr+3cGifyYSf2wYmbQk4rmkRegl8jxGyVwgvbhRQYXqP3rnKCCVeHHLyGRdpSgXapRdpamWburgogGvCPuUxGi2GbXHrqauW5+Q3+Nx59fk+GSq9QsdVBCNgIesuCJPK2Fzd+3DRapf3vkYM1dCIWxaEMxfZ7yBw9F0p44ceK48H86IHKAWLQVJFpqfQSec1tsB0xMlhQbu57yW1blUD3mw043DRG00VVwdfGvSlww3dNKHMfq0jetcdQKBgQDO0EC3DIJVnRKiqHCYYaMQANY1LJbo5/00Cj9f5AamqzFrKMeBQUOGNDI4ImaODEQClH8kJ8UxbvNaKtd80DgykgoC5KHDYAgD/cfOoLZklUwhDToOOCbB/doIkcRPz8D4IjJMCArGKOBGTU0/wnvi198uP9SnHu6kVUqi4luMkwKBgQCyfKWmKj6hwMf9MRZAR/05wZRAhM2opIC+UsSiUNtqq/x+uabHrWLDHGDg+Bs4K1SGqP54JL9TtSoVDpW8s/W2o/e2PmggnBMXknMVh6XdKDGDknvpPkrfn4eU5vBTTkWSo3n/i/lr0B9y56zs4BmT8puukaeNTs8DmbZFpGLy3wKBgQCu4MgoQOqg7F4KrvXu1HjnGl4HvrmpHiosbBHkytOY8Z+rJmN097Pacp6YevZPXS3RJLJR51NGlrQuZZWrlAJBON5ATgY/SB/fCCSBo8I3UUt7k0ypI3Aaj8ZjgqcGef2JlJkFU7OQlpX2vHz60kChs7qjyBPQcUaILRscmt2hAwKBgHtEJG6+rCSv7C5LUU+xqAvUShvI2/PFMHjA1rY222IvRyEJ/EnYz+Vp2upjzwCaxDR/z8aPsmcQLfD+8qCxNFgfo3sEXItw3qqf5MUQupcjJBb2+oaaE8Ge/lbyqzUNUHU2W5IGTIqN4LpNkBrDpkaL/cKxKyMnM0cMSFqObhvHAoGBAM3Y93FjFVA8Aj47zgSpqsiPsSsIagGJWycWfBK7oBQoFqNTx23vOdFBK/zo09UUA6rKesVWttslSIUBZntTVELGLm3Y61HJJ+4QDB4hxYcBYHQMQFIItgx2ey+DZodeEQ95JZf2J37ARY/ydszZJuw/i4m7AYRHSUELspMRN7/b";//�̳̲鿴��ȡ��ʽ���������ã�

	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3unuegrZFxFR45ZxdTvXNPQ3/xMwf+FJs3qamirIe+x9Z4qzcCchlGCFAIOmR2SrUVsF31uZ9F2/xwkjovwkfPIzvVabW1UOnKXNNbgQkdZYKm9rainNoV2HZpy4pzfVOVvMEHBkpQa1gmcrJyHwcaeVy8Co1GbGwoeEULah3cMX4chOFitBtfKKU5VNSyvqo/Ms8ZD7xi0GxHcZlbrFfLhhhLdpt5oL6cpIZjK17gmIyctrkw/m7LU8soSlljSQKbHCwllE/gJM0Fg56Km+q1RyH+1gJKgA4b8n6AISKE6ga6hId5k7Gfy9SfCJtTCuh4pBai/VEhGHyVyonUHCUQIDAQAB";//�̳̲鿴��ȡ��ʽ���������ã�
	
	public static String notify_url = "http://localhost:8080/alipay/alipayNotifyNotice";
	
	public static String return_url = "http://localhost:8080/alipay/alipayReturnNotice";
	
	public static String sign_type = "RSA2";
	
	public static String charset = "utf-8";
	
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//ע�⣺ɳ����Ի�������ʽ����Ϊ��https://openapi.alipay.com/gateway.do
}
