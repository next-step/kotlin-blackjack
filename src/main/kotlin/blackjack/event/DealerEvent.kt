package blackjack.event

private typealias HitEvent = () -> Unit

class DealerEvent(val hitEvent: HitEvent)
