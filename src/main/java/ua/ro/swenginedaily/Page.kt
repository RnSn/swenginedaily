package ua.ro.swenginedaily

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.*

class Page constructor(link: String) {

    val in_format = SimpleDateFormat("yyyy/MM/dd")
    val out_format = SimpleDateFormat("yyyy.MM.dd_")

    private val page: Document? = Jsoup.connect(link)
            .userAgent("Mozilla").get()

    fun titles(): List<String> {
        if (page == null) {
            return Collections.emptyList()
        }
        val titles = page.getElementsByAttributeValue("class", "post-title")

        return titles.map {
            val anchor = it.allElements.last()
            val date = in_format.parse(anchor.attr("href").replaceFirst(BASE_URL, ""))
            "${out_format.format(date)}${anchor.text().replace(" ", "_")}"
        }.toList()
    }
}



