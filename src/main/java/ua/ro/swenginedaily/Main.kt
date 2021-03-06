package ua.ro.swenginedaily

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.nio.file.Paths

val BASE_URL = "http://softwareengineeringdaily.com/"

fun main(args: Array<String>) {
    val dest = if (args.size > 0) {
        args[0]
    } else {
        "d:/AUDIO_VIDEO/softwareengineeringdaily"
    }

    for (i in 1..lastPage()) {
        val title_links = Page(BASE_URL + "page/" + i).title_links()
        println("Processing page #$i with ${title_links.size} podcasts")
        title_links.forEach { entry ->
            Podcast(entry.key, entry.value, Paths.get(dest)).dl()
        }
    }
}

private fun lastPage(): Int {
    val page: Document? = Jsoup.connect(BASE_URL).userAgent("Mozilla").get()
    val pageNumbers = page!!.getElementsByAttributeValue("class", "page-numbers")
    return pageNumbers.last().text().toInt()
}