package dsl

data class Resume(val name: String, val company: String)

class ResumeBuilder {
    var name: String = ""
    var company: String = ""

    fun name(name: String) {
        this.name = name
    }

    fun company(name: String) {
        this.company = name
    }

    fun build(): Resume {
        return Resume(name, company)
    }
}

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}
