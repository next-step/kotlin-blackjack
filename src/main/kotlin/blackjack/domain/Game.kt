package blackjack.domain

import kotlin.reflect.KClass

class Game(val players: Players, val deck: Deck = Deck.getDeck()) {

    private val phases: List<Phase>
    private var phaseIndex: Int = 0
    private val phase
        get() = phases[phaseIndex]

    constructor(names: List<String>) : this(names.map(::Player).let(::Players))

    init {
        players.values.forEach {
            it.initialize(deck)
        }
        phases = players.values.map(Phase::PlayerPhase) + players.let(Phase::EndPhase)
    }

    fun moveToNextPhase() {
        check(phase.isFinish()) { "current phase is not over" }
        check(phaseIndex < phases.size) { "there is no more next phase" }
        phaseIndex++
    }

    fun <T : Phase> checkAndGetPhase(phaseType: KClass<T>): T {
        check(phaseType.isInstance(phase)) { "not ${phaseType::class.java.name}" }
        return phase as T
    }

}
