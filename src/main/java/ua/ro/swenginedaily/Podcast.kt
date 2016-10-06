package ua.ro.swenginedaily

import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels
import java.nio.file.Files
import java.nio.file.Path

class Podcast(val title: String, val link: String, val dest: Path) {

    val _1_MB = 1048576.0
    val TRANSFTER_COUNT_MB: Long = 10485760 * 2

    init {
        if (!Files.exists(dest)) {
            Files.createDirectory(dest)
        }
    }

    fun dl() {
        val target = dest.resolve(title + ".mp3")
        if (Files.exists(target)) {
            return
        } else {
            val website = URL(link)
            Channels.newChannel(website.openStream()).use({ rbc ->
                FileOutputStream(target.toFile()).use({ fos ->
                    var transfered: Long
                    var total: Long = 0
                    transfered = fos.channel.transferFrom(rbc, total, TRANSFTER_COUNT_MB)

                    while (transfered == TRANSFTER_COUNT_MB) {
                        total += transfered
                        System.out.printf("Transfered %.2f MB - %s\n", total / _1_MB,
                                target.fileName)
                        transfered = fos.channel.transferFrom(rbc, total, TRANSFTER_COUNT_MB)
                    }
                })
            })
        }
    }
}