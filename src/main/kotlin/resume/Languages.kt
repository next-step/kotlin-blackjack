package resume

class Languages(
    val values: List<Language> = emptyList(),
) {
    val size get() = values.size

    operator fun get(index: Int) = values[index]
}
