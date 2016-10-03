package ua.ro.swenginedaily

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat

val BASE_URL = "http://softwareengineeringdaily.com/"

fun main(args: Array<String>) {
    val page: Document? = Jsoup.connect(BASE_URL + "page/" + 2).userAgent("Mozilla").get()

    val titles = page?.getElementsByAttributeValue("class", "post-title")

    val in_format = SimpleDateFormat("yyyy/MM/dd")
    val out_format = SimpleDateFormat("yyyy.MM.dd_")
    titles?.forEach {
        val anchor = it.allElements.last()
        val date = in_format.parse(anchor.attr("href").replaceFirst(BASE_URL, ""))
        println("${out_format.format(date)}${anchor.text().replace(" ", "_")}")
    }


}