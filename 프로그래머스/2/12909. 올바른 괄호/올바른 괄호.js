/**
문자열을 배열로 변환
맨처음 )이 먼저나오면 return false
하나씩 queue에 집어넣기
input이 )이고 queue top이 '('라면 top을 queue에서 제거
input이 (라면 그냥 queue에 넣기
queue가 비어있다면 true

*/

function solution(s){
    const inputs = s.split('');
    const queue = [];
    
    if (inputs[0] === ')') return false;
    
    for (const input of inputs) {
        if (input === ')') {
            if (queue.at(-1) === '(') queue.pop();       
            else return false;
        } 
        
        if (input === '(') {
            queue.push(input);
        }
    }
    
    return queue.length === 0;
}