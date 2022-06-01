package study

data class Resume(
    val name: String,
    val company: String,
    val skills: List<String>,
    val language: List<String>
) {
}

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}

class ResumeBuilder(
    private var name: String,
    private var company: String
) {
    fun build(): Resume {
        return Resume(
            name,
            company,
            skills,
            languege
        )
    }

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }
}