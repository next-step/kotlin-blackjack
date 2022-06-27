package blackjack.domain

class HitEvent {
    private lateinit var participant: Participant

    private var observer: ((Participant) -> Unit)? = null

    fun observe(observer: (Participant) -> Unit) {
        this.observer = observer
    }

    fun emit(participant: Participant) {
        this.participant = participant
        observer?.invoke(participant)
    }
}
