package dsl

data class Resume(val name: String)

class ResumeBuilder {
    var name: String = ""

    fun name(name: String) {
        this.name = name
    }

    fun build(): Resume {
        return Resume(name)
    }
}

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}
