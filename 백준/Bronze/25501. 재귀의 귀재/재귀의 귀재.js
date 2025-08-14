const fs = require('fs');
const input = fs.readFileSync(0).toString().trim().split('\n');
const N = parseInt(input[0]);
const strings = input.slice(1, N + 1);
let calls = 0;

function isPalindrome(str) {
    calls = 0;
    let l = 0;
    let r = str.length - 1;

    
    const res = recursion(str, l, r);
    return res + ' ' + calls;
}

function recursion(s, l, r) {
    calls++;
    if(l >= r) return 1;
    else if(s[l] != s[r]) return 0;
    else return recursion(s, l+1, r-1);
}

for (const s of strings) {
    console.log(isPalindrome(s));
}