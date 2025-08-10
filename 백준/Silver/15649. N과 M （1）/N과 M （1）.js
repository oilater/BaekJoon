const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split(' ').map(Number);
const [N, M] = input;

// const N = 4;
// const M = 2;
const picked = [];
let result = '';

function permutation(depth) {
    if (depth === M) {
        result += `${picked.join(' ')}\n`;
        return;
    }
    
    // 현재 i가 cur과 같으면 넘겨야 한다
    for (let i = 1; i <= N; i++) {
        if (picked.includes(i)) continue; // 쓰지 말기
        picked.push(i); // 일단 i를 넣어
        permutation(depth + 1); // 다음건 뽑아야지

        picked.pop();
    }
    
}

permutation(0);
console.log(result);