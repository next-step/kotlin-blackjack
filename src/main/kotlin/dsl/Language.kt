package dsl

data class Language(val name: String, val level: Int)

class LanguageBuilder {
  val languages: MutableList<Language> = mutableListOf()

  infix fun String.level(level: Int) {
    languages.add(Language(this, level))
  }

  fun build(): List<Language> = languages.toList()
}