package ua.ro.swenginedaily

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

val BASE_URL = "http://softwareengineeringdaily.com/"

fun main(args: Array<String>) {

    for (i in 1..lastPage()) {
        println(Page(BASE_URL + "page/" + i).titles())
    }
}

private fun lastPage(): Int {
    val page: Document? = Jsoup.connect(BASE_URL).userAgent("Mozilla").get()

    val pageNumbers = page!!.getElementsByAttributeValue("class", "page-numbers")
    return pageNumbers.last().text().toInt()
}