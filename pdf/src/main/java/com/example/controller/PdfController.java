package com.example.controller;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 控制器类，用于处理PDF模板填充及下载请求
 */
@RestController
public class PdfController {

    /**
     * 处理GET请求以下载填充了数据的PDF文件
     *
     * @param response HttpServletResponse对象，用于设置响应头和发送下载文件
     * @return 响应实体，包含填充好数据的PDF字节流
     * @throws IOException 如果读取或写入PDF文件时发生异常
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> test(HttpServletResponse response) throws IOException {
        // 设置响应头，指示浏览器以附件形式下载文件，并设置文件名
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String downloadFileName = System.currentTimeMillis() + ".pdf";
        response.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);

        // 准备需要填充到PDF模板中的数据
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", "张三");
        dataMap.put("idCard", "123456");

        // 填充数据并生成带数据的PDF字节流
        byte[] pdfBytes = getPdf(dataMap);

        // 创建并返回包含填充后PDF字节流的响应实体
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.CREATED);
    }

    /**
     * 根据提供的数据填充PDF模板并返回填充后的PDF字节流
     *
     * @param dataMap 需要填充到PDF模板中的键值对数据
     * @return 填充好数据的PDF文件字节数组
     * @throws IOException 如果读取或写入PDF文件时发生异常
     */
    private byte[] getPdf(Map<String, String> dataMap) throws IOException {
        // 获取PDF模板文件路径
        String sourcePdf = ResourceUtils.getURL("classpath:").getPath() + "/templates/2.pdf";

        // 使用PDF阅读器加载模板文件
        PdfReader pdfReader = new PdfReader(new FileInputStream(sourcePdf));

        // 创建一个内存输出流用于存储填充好数据的PDF文件
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 创建PDF文档对象，连接读取器和输出流
        PdfDocument pdf = new PdfDocument(pdfReader, new PdfWriter(outputStream));

        // 设置默认页面大小为A4
        pdf.setDefaultPageSize(PageSize.A4);

        // 获取PDF表单域对象
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
        Map<String, PdfFormField> fields = form.getFormFields();

        // 设置字体，这里使用的是"STSong-Light"字体
        PdfFont currentFont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", PdfFontFactory.EmbeddingStrategy.PREFER_NOT_EMBEDDED);

        // 遍历待填充的数据，并将其填入对应的表单域
        dataMap.forEach((key, value) -> {
            Optional<PdfFormField> formFieldOptional = Optional.ofNullable(fields.get(key));
            formFieldOptional.ifPresent(formField -> {
                // 设置字体并替换表单域的值
                formField.setFont(currentFont).setValue(value);
            });
        });

        // 锁定并合并所有表单域，使其无法再编辑
        form.flattenFields();

        // 关闭PDF文档，释放资源
        pdf.close();

        // 将填充好的PDF文件转换为字节数组并返回
        return outputStream.toByteArray();
    }
}