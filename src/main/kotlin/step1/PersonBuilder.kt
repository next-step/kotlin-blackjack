package step1

class PersonBuilder {
    private var name: String = DEFAULT_NAME
    private var company: String = DEFAULT_COMPANY

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun build(): Person {
        return Person(name, company)
    }

    companion object {
        const val DEFAULT_NAME: String = ""
        const val DEFAULT_COMPANY: String = ""
    }
}
