package view

enum class YesOrNo(
    val value: String,
) {
    YES("y"),
    NO("n"), ;

    companion object {
        fun of(input: String): YesOrNo? = values().firstOrNull { it.value == input }
    }
}
