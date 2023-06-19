package blackjack.event

private typealias HitMessageEvent = () -> Unit

class DealerEvent(val hitMessageEvent: HitMessageEvent)
