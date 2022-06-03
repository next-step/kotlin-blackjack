package dsl.domain

/**
 * 클래스에 대한 설명을 적어주세요.
 * Created by Jaesungchi on 2022.06.03..
 */
class Skills(
    softs: List<Skill> = emptyList(),
    hards: List<Skill> = emptyList()
) {
    private val _softs = softs.toMutableList()
    val softs: List<Skill> get() = _softs.toList()

    private val _hards = hards.toMutableList()
    val hards: List<Skill> get() = _hards.toList()

    fun addSoft(skill: Skill) {
        _softs.add(skill)
    }

    fun addHard(skill: Skill) {
        _hards.add(skill)
    }
}
