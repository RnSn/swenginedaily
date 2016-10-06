package ua.ro.swenginedaily

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.*

class Page constructor(link: String) {

    private val in_format = SimpleDateFormat("yyyy/MM/dd")
    private val out_format = SimpleDateFormat("yyyy.MM.dd_")

    private val page: Document? = Jsoup.connect(link)
            .userAgent("Mozilla").get()

    private val attr_class = "class"
    private val attr_href = "href"

    fun title_links(): Map<String, String> {
        if (page == null) {
            return Collections.emptyMap()
        }


        val titles = page.getElementsByAttributeValue(attr_class, "post-title")
        val links = page.getElementsByAttributeValue(attr_class, "powerpress_link_d")

        val title_links = HashMap<String, String>()
        titles.forEachIndexed { index, element ->
            val anchor = element.allElements.last()
            val date = in_format.parse(anchor.attr(attr_href).replaceFirst(BASE_URL, ""))
            title_links["${out_format.format(date)}${anchor.text().replace(" ", "_")}"] =
                    links[index].attr(attr_href)
        }

        return title_links
    }
}
