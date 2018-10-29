package ThreadTest.TreadPool;


import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class DownloadService {

    //下载文件
    public void doFile(HttpServletRequest request, HttpServletResponse resp) throws IOException {

        String path = request.getParameter("path");
        String fileName = request.getParameter("filename");


        fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
        String fileSaveRootPath = request.getSession().getServletContext().getRealPath(path);
        //String xpath = findFileSavePathByFileName(fileName, fileSaveRootPath);
        //File file = new File(xpath + "\\" + fileName);
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("下载文件不存在!");
            return;
        }

        String realname = fileName.substring(fileName.indexOf("_") + 1);

        //设置响应头，控制浏览器下载该文件
        resp.setContentType("application/x-msdownload");
        //设置头信息
        //resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");


        FileInputStream in = new FileInputStream(file);
        ServletOutputStream out;

        out = resp.getOutputStream();

        byte buffer[] = new byte[2048];
        int len = 0;


        in.read(buffer);
        out.write(buffer);

        //while ((len = in.read(buffer)) >0) {
        //    out.write(buffer, 0, len);
        //}

        in.close();
        out.close();
        out.flush();

    }

    public String findFileSavePathByFileName(String filename,String saveRootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }

}
