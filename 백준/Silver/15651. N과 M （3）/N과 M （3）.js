const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split(' ').map(Number);
const [N, M] = input;

// N 길이의 배열을 만들기
const arr = Array.from({length: N}, (_, i) => i + 1);
const res = [];
let result = '';

function permutation(depth) {
    if (depth === M) {
        result += `${res.join(' ')}\n`;
        return;
    }

    for (let i = 1; i <= N; i++) {
        res.push(i);
        permutation(depth + 1);
        res.pop();
    }
}

permutation(0);
console.log(result);
