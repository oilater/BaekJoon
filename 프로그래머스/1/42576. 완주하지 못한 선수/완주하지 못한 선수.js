function solution(participant, completion) {
    const obj = {}
    
    for (const part of participant) {
        if (part in obj) {
            obj[part]++;
        } else {
            obj[part] = 1;
        }
    }
    
    for (const comp of completion) {
        obj[comp]--;
    }
    
    for (const a in obj) {
        if (obj[a] > 0) return a;
    }
}