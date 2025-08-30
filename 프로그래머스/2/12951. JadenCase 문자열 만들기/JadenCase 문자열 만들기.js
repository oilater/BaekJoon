function solution(s) {
  return s
    .split(/\s/) // 공백 문자 기준으로 나누기 (연속 공백 포함)
    .map(word => {
      if (word === "") return ""; // 공백 유지
      return word[0].toUpperCase() + word.slice(1).toLowerCase();
    })
    .join(" ");
}
