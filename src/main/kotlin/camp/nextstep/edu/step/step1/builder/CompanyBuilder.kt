package camp.nextstep.edu.step.step1.builder

import camp.nextstep.edu.step.step1.domain.Company

class CompanyBuilder {

    private lateinit var name: String

    fun name(name: String) {
        this.name = name
    }

    fun build(): Company {
        return Company(name = name)
    }

}
