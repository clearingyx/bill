package com.biz.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang.peng on 2016/8/3.
 */
public class ConnectUtil {
    /**
     * get访问
     * @param url
     * @return
     */
    public static Map<String,Object> getConnectForGetBackMap(String url){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        //配置请求的超时设置
        /*RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(200)
                .setConnectTimeout(200)
                .setSocketTimeout(200).build();*/
        httpGet.setConfig(RequestConfig.DEFAULT);

        CloseableHttpResponse response;
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //发送请求
            response = httpClient.execute(httpGet);

            //返回信息
            HttpEntity entity = response.getEntity();
            String res = EntityUtils.toString(entity);
            //res = java.net.URLEncoder.encode(res, "utf-8");
            map = JSONUtil.toMap(res);
            httpClient.close();
        } catch (IOException e) {
            //写入日志，返回错误
        } finally{
            httpGet.releaseConnection();
        }
        return map;
    }
    /**
     * get访问
     * @param url
     * @return
     */
    public static String getConnectForGetBackJson(String url){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        httpGet.setConfig(RequestConfig.DEFAULT);

        CloseableHttpResponse response;
        String res = "";
        try {
            //发送请求
            response = httpClient.execute(httpGet);

            //返回信息
            HttpEntity entity = response.getEntity();
            res = EntityUtils.toString(entity);
            httpClient.close();
        } catch (IOException e) {
            //写入日志，返回错误
        } finally{
            httpGet.releaseConnection();
        }
        return res;
    }

    /**
     * POST方式发起http请求
     */
    public static Map getConnectForPost(String url,String json){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        Map<String,Object> map = new HashMap<String, Object>();
        try {
            StringEntity se = new StringEntity(json);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            httpPost.setEntity(se);

            //post请求
            HttpResponse httpResponse = httpClient.execute(httpPost);

            //得到返回信息
            HttpEntity httpEntity = httpResponse.getEntity();
            map = new HashMap<String, Object>();
            String res;
            res = EntityUtils.toString(httpEntity, "utf-8");
            map = JSONUtil.toMap(res);
            //释放资源
            httpClient.close();
        }catch (IOException e){
            //写入日志，返回错误
        } finally {
            httpPost.releaseConnection();
        }
        return map;
    }

    /**
     * 发送多媒体文件
     * @param url
     * @param path
     * @return
     * @throws Exception
     */
    public static Map getConnectForFile(String url,String path) throws Exception {
        //定义返回的map
        Map map = new HashMap();

        // 获得utf-8编码的mbuilder
        MultipartEntityBuilder mBuilder = get_COMPATIBLE_Builder("UTF-8");
        //传参，微信的access_token和type是作为get传递的，就不用post传参了
        //mBuilder.addTextBody("type", "image");
        // 这里就是我要上传到服务器的多媒体图片
        mBuilder.addBinaryBody("media", new File(path), ContentType.APPLICATION_OCTET_STREAM, new File(path).getName());

        // 建造我们的http多媒体对象
        HttpEntity httpEntity = mBuilder.build();
        // 建立一个sslcontext，这里我们信任任何的证书。
        SSLContext context = getTrustAllSSLContext();
        // 建立socket工厂
        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(context);
        // 建立连接器
        CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(factory).build();
        try {
            // 得到一个post请求的实体
            HttpPost post = getMultipartPost(url);
            // 给请求添加参数
            post.setEntity(httpEntity);
            // 执行请求并获得结果
            CloseableHttpResponse reponse = client.execute(post);
            try {
                // 获得返回的内容
                HttpEntity entity = reponse.getEntity();
                // 输出
                //System.out.println(EntityUtils.toString(entity));
                String res = EntityUtils.toString(entity, "utf-8");
                map = JSONUtil.toMap(res);
                // 消耗实体
                EntityUtils.consume(entity);
            } finally {
                reponse.close();
            }
        } finally {
            client.close();
        }
        return map;
    }
    private static String getBoundaryStr(String str) {
        return "------------" + str;
    }

    private static MultipartEntityBuilder get_COMPATIBLE_Builder(String charSet) {
        MultipartEntityBuilder result = MultipartEntityBuilder.create();
        result.setBoundary(getBoundaryStr("7da2e536604c8"))
                .setCharset(Charset.forName(charSet))
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        return result;
    }

    private static HttpPost getMultipartPost(String url) {
        /* 这里设置一些post的头部信息，具体求百度吧 */
        HttpPost post = new HttpPost(url);
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Accept", "*/*");
        post.addHeader("Content-Type", "multipart/form-data;boundary="
                + getBoundaryStr("7da2e536604c8"));
        post.addHeader("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        return post;
    }

    private static SSLContext getTrustAllSSLContext() throws Exception {
        SSLContext context = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustStrategy() {
                    public boolean isTrusted(X509Certificate[] arg0, String arg1)
                            throws CertificateException {
                        // 这一句就是信任任何的证书，当然你也可以去验证微信服务器的真实性
                        return true;
                    }
                }).build();
        return context;
    }
}
