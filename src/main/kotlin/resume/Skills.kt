package resume

class Skills(
    val values: List<Skill> = emptyList(),
) {
    val size get() = values.size

    operator fun get(index: Int) = values[index]
}
