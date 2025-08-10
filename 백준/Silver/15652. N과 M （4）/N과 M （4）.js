const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split(' ').map(Number);
const [N, M] = input;

const picked = [];
let result = '';

function permutation(start, depth) {
    if (depth === M) {
        result += `${picked.join(' ')}\n`;
        return;
    }

    for (let i = start; i <= N; i++) {
        picked.push(i);
        permutation(i, depth + 1);
        picked.pop();
    }
}

permutation(1, 0);
console.log(result);