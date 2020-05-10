/**
 * Project Name: time-travle.github.io
 * File Name: PrintPdfAction
 * Package Name: org.joven.common.utils
 * Date: 2020/5/5 21:44
 * Copyright (c) 2020,All Rights Reserved.
 */
package org.joven.common.utils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.joven.common.common.CommonConstants.CharsetType.GB_18030;
import static org.joven.common.common.CommonConstants.CharsetType.ISO8859_1;

/**
 * CreateBy Administrator
 * Date: 2020/5/5 21:44
 * Version:
 * Remark: 打印PDF报表
 */
public class PrintPDFAction {
    public void printPdfFromXML(HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fin = null;
        String templateName = request.getParameter("templateName");
        String templatePathFull = request.getParameter("templatePathFull") + templateName;
        JRXmlDataSource xmlDataSource_F = getXmlDataSource(request);
        Map<String, Object> xmlDataSource_P = getStaticDataSource(request);
        response.setContentType("application/pdf");
        try {
            response.setHeader("Content-Disposition", "inline:filename=\""
                    + new String("Documents".getBytes(GB_18030), ISO8859_1)
                    + ".pdf" + "\"");
            fin = new FileInputStream(templatePathFull);
            JasperRunManager.runReportToPdfStream(fin, response.getOutputStream(), xmlDataSource_P, xmlDataSource_F);
            response.getOutputStream().flush();
        } catch (IOException | JRException e) {
            e.printStackTrace();
        } finally {
            if (null != fin) {
                try {
                    fin.close();
                    response.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拼装静态参数如图片地址 模板取用方式为 $P{}
     *
     * @param request request
     * @return Map
     */
    private Map<String, Object> getStaticDataSource(HttpServletRequest request) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("image_dir", "/templates/images/happy.png");
        return parameterMap;
    }

    /**
     * 拼装动态态参数如图片地址 模板取用方式为 $F{}
     * xml格式为:
     * <root>
     * <body>
     * <Field_1>
     * <value>value</value>
     * </Field_1>
     * </body>
     * </root>
     *
     * @param request request
     * @return JRXmlDataSource
     */
    private JRXmlDataSource getXmlDataSource(HttpServletRequest request) {
        String xmlStringData = request.getParameter("printDataXMLString");
        String xmlHeader = "<?xml version = \"1.0\" encoding = \"UTF-8\"?>";
        String xmlStringDataTemp = xmlHeader + xmlStringData;
        try {
            String xmlStringDataTemp2 =
                    new String(xmlStringDataTemp.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            Document doc = parseText(xmlStringDataTemp2);
            return new JRXmlDataSource(doc).dataSource("/root/body");
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document parseText(String xmlString) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder bd;
        try {
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            bd = dbf.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(xmlString.trim()));
            return bd.parse(source);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
