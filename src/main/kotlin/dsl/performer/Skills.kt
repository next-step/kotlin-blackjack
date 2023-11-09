package dsl.performer

data class Skills(
    val soft: List<String>,
    val hard: List<String>,

) {
    override fun toString(): String {
        return "Skills(soft=$soft, hard=$hard)"
    }
}
