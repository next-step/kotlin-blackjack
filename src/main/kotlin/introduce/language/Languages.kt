package introduce.language

import introduce.IntroduceBuilder

@JvmInline
value class Languages(
    val values: List<Language> = listOf(),
)

class LanguagesBuilder : IntroduceBuilder<Languages> {
    private val values: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        values.add(Language(this, level))
    }

    override fun build(): Languages = Languages(values.toList())
}
