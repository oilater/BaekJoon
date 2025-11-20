/**
1개를 고를 때 : 각 해시키의 값들의 합
2개를 고를 때 : 두개씩 *
3개를 고를 때 : 3개씩 * 
2개가 있다면? ab + a + b
3개가 있다면? abc + ab + bc + ac + a + b + c

...
(1 + a)(1 + b)(1 + c) - 1;
*/

function solution(clothes) {
    const hashMap = {};
    
    for (const cloth of clothes) {
        hashMap[cloth.at(-1)] = 0;
    }
    
    for (const cloth of clothes) {
        hashMap[cloth.at(-1)]++;
    }
    
    let res = 1;
    for (const key in hashMap) {
        
        res *= (1 + hashMap[key]);
    }

    return res - 1;
}