package ua.ro.swenginedaily

import java.nio.file.Files
import java.nio.file.Path

class Podcast (val title: String, val link: String, val dest: Path) {

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
            // TODO download


        }
    }
}