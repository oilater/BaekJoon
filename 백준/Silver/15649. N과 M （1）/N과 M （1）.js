const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split(' ').map(Number);
const [N, M] = input;

const picked = [];
const visited = Array.from({length: N}, (_) => false);
let result = '';

function permutation(depth) {
    if (depth === M) {
        result += `${picked.join(' ')}\n`;
        return;
    }
    
    for (let i = 1; i <= N; i++) {
        if (visited[i]) continue; // 이미 선택했다면 넘김
        picked.push(i); // 일단 i를 넣어
        visited[i] = true; // 선택 체크

        permutation(depth + 1); // 다음건 뽑아야지

        picked.pop();
        visited[i] = false;
    }
}

permutation(0);
console.log(result);