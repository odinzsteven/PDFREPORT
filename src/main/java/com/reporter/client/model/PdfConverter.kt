package com.reporter.client.model

import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.HtmlConverter
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.CompressionConstants
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.font.FontProvider
import com.itextpdf.layout.font.FontSet
import java.io.InputStream
import java.io.OutputStream

object PdfConverter {
    private val fontDirs = arrayOf(
        "Amiri",
        "Cairo",
        "Lateef",
        "MarkaziText",
        "NotoNaskhArabic",
        "ReadexPro",
        "ScheherazadeNew",
        "Vazirmatn",
    )

    private val fontSets: FontSet = loadFontSet()

    private fun loadFontSet() = FontSet().apply {
        //     resourceRetriever.loadFontsBlocking(fontNamesProvider.get()).forEach { resource ->
        //         addFont(resource, PdfEncodings.IDENTITY_H)
        //     }

        fontDirs.forEach { dir ->
            readFontResource(loadResource("fonts/$dir/Bold.ttf"))
            readFontResource(loadResource("fonts/$dir/Regular.ttf"))
        }
    }

    private fun loadResource(path: String): InputStream? =
        javaClass.classLoader.getResourceAsStream(path)

    private fun FontSet.readFontResource(input: InputStream?) = input?.let {
        this.addFont(it.readAllBytes(), com.itextpdf.io.font.PdfEncodings.IDENTITY_H)
    }

    private fun buildConverterProperties(workdir: String): ConverterProperties =
        ConverterProperties().apply {
            resourceRetriever = IResourceRetrieverImpl(workdir)
            isImmediateFlush = false
            fontProvider = FontProvider(fontSets, "Helvetica")
        }

    fun generatePDF(workDir: String, html: String, outputStream: OutputStream) {
        val pdfWriter = PdfWriter(outputStream).apply {
            compressionLevel = CompressionConstants.BEST_COMPRESSION
            setSmartMode(true)
        }

        PdfDocument(pdfWriter).apply {
            defaultPageSize = PageSize.A4
        }.use { pdfDocument ->
            val document = HtmlConverter.convertToDocument(
                html,
                pdfDocument,
                buildConverterProperties(workDir)
            )
            document.setMargins(0f, 0f, 0f, 0f)
            document.relayout()
            document.flush()
        }
    }
}