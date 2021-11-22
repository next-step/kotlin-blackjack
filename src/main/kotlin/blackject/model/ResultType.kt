package blackject.model

// Win    딜러보다 21에 가까움. 베팅한 금액만큼 딜러에게 받음.
// Push   딜러와 동일 (푸쉬 Push / 스탠드오프 Stand-off). 베팅 금액을 돌려받음.
// Lose    딜러가 21에 더 가까움. 베팅 금액을 딜러에게 줌.
enum class ResultType(val string: String) {
    WIN("승"), LOSE("패"), BUST("패"), PUSH("무승부")
}
