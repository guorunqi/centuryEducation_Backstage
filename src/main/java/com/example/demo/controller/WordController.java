package com.example.demo.controller;

import com.example.demo.Word.WordAction;
import com.example.demo.domain.ControllerReturn;
import com.example.demo.domain.PolicyDocument;
import com.example.demo.domain.PolicyDocumentEntry;
import com.example.demo.domain.PolicyDocumentEntryExample;
import com.example.demo.service.PolicyDocumentEntryService;
import com.example.demo.service.PolicyDocumentService;
import com.example.demo.util.TreeTablePojo;
import com.example.demo.util.TreeTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WordController {
    private String FILE_PATH = "D:\\doc_f\\";
    private String FILE_NAME = "downLoadWord20190201_86.doc";
    @Autowired
    private PolicyDocumentService policyDocumentService;
    @Autowired
    private PolicyDocumentEntryService policyDocumentEntryService;
    @Resource
    private ResourceLoader resourceLoader;
    @ResponseBody
    @RequestMapping("/downPolicydocumentManagementWord")
    public ControllerReturn downPolicydocumentManagementWord(String PolicyDocumentId) {
        ControllerReturn controllerReturn = new ControllerReturn();
        TreeTableUtil treeTableUtil = new TreeTableUtil();
        WordAction wordAction = new WordAction();
        try {
            PolicyDocument policyDocument = policyDocumentService.selectPolicyDocumentById(PolicyDocumentId);
            PolicyDocumentEntryExample policyDocumentEntryExample = new PolicyDocumentEntryExample();
            policyDocumentEntryExample.or().andPolicyDocumentIdEqualTo(PolicyDocumentId);
            List<PolicyDocumentEntry> PolicyDocumentEntryList = policyDocumentEntryService.selectPolicyDocumentEntryByExample(policyDocumentEntryExample);
            List<TreeTablePojo> treeTable = treeTableUtil.PolicyDocumentEntryToTree(PolicyDocumentEntryList);
            List<TreeTablePojo> treeTableData = treeTableUtil.getTree(treeTable);
            Map map = new HashMap();
            map.put("policyDocument", policyDocument);
            map.put("treeTableData", treeTableData);

            String filePathName = wordAction.createWord(map);
            controllerReturn.setData(filePathName);
            controllerReturn.setCode("true");
            return controllerReturn;
        } catch (Exception e) {
            controllerReturn.setCode("false");
            controllerReturn.setMessage(e.toString());
            return controllerReturn;
        }
    }

    //文件下载相关代码
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response,String Name) {
                String filepath = FILE_PATH + Name;
                OutputStream output = null;
                try {
                    //清空缓存
                    response.reset();
                    // 定义浏览器响应表头，并定义下载名
                    String fileName = URLEncoder.encode("ExportWord.doc", "UTF-8");
                    response.setHeader("Content-disposition", "attachment;filename="+fileName);
                    //定义下载的类型，标明是word文件
                    response.setContentType("application/msword;charset=UTF-8");
                    //把创建好的word写入到输出流

                    FileInputStream inputStream = new FileInputStream(filepath);
                    byte[] buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);
                    output = response.getOutputStream();
                    output.write(buffer);
                    //随手关门
                    output.close();
                }catch (IOException e){
                    e.printStackTrace();
            }
        }
}
