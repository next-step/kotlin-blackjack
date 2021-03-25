package study.domain

fun introduce(init: IntroduceDsl.() -> Unit): Resume {
    return IntroduceDsl()
        .apply(init)
        .build()
}

class IntroduceDsl {
    lateinit var name: String

    var company: String? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun build(): Resume {
        return Resume(
            name = Name(name),
            company = company?.let { Company(it) }
        )
    }
}
