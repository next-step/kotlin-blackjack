package study.domain

class Resume {
    lateinit var name: String

    var company: String? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }
}
