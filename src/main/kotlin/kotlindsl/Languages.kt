package kotlindsl

import kotlindsl.util.AddOnlyMutableList

data class Language(val language: String, val level: Int)
class Languages : AddOnlyMutableList<Language>() {
    infix fun String.level(level: Int) {
        this@Languages.add(Language(this, level))
    }
}
