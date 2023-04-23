package com.reporter.client.model

import com.itextpdf.html2pdf.ConverterProperties
import com.itextpdf.html2pdf.HtmlConverter
import com.itextpdf.io.font.PdfEncodings
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.CompressionConstants
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.font.FontProvider
import com.itextpdf.layout.font.FontSet
import java.io.OutputStream
import java.util.function.Supplier

class PdfConverter(
    //val fontNamesProvider: Supplier<Collection<String>> = { emptyList<String>() },
    val htmlProvider: Supplier<String>,
) {

    val resourceRetriever: PdfResourceRetriever = IResourceRetrieverImpl()

    private val fontSets = loadFontSet()

    private fun loadFontSet() = FontSet().apply {
        //resourceRetriever.loadFontsBlocking(fontNamesProvider.get()).forEach { resource ->
            //addFont(resource, PdfEncodings.IDENTITY_H)
        //}

        //addFont(readFile("cario/regular.ttl"), PdfEncodings.IDENTITY_H)
        //addFont(readFile("cario/bold.ttl"), PdfEncodings.IDENTITY_H)
    }

    private fun buildConverterProperties(): ConverterProperties =
        ConverterProperties().apply {
            resourceRetriever = resourceRetriever
            isImmediateFlush = false
            fontProvider = FontProvider(fontSets, "Helvetica")
        }

    fun generatePDF(outputStream: OutputStream) {
        val pdfWriter = PdfWriter(outputStream).apply {
            compressionLevel = CompressionConstants.BEST_COMPRESSION
            setSmartMode(true)
        }

        PdfDocument(pdfWriter).apply {
            defaultPageSize = PageSize.A4
        }.use { pdfDocument ->
            val document = HtmlConverter.convertToDocument(
                htmlProvider.get(),
                pdfDocument,
                buildConverterProperties()
            )
            document.setMargins(0f, 0f, 0f, 0f)
            document.relayout()
            document.flush()
        }
    }
}