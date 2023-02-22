//	<!-- chessboard.js v1.0.0 | (c) 2019 Chris Oakman | MIT License chessboardjs.com/license -->
var playLondon = function (APIconfigFiller, newPos) {

  var config = {
    orientation: 'white',
    draggable: true,
    position: 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR',
    onChange: playLondon
  }
  board = Chessboard('myBoard', config)



  if (Chessboard.objToFen(newPos) == 'rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR') {

    var config = {
      orientation: 'white',
      draggable: true,
      position: 'rnbqkbnr/ppp1pppp/8/3p4/3P4/8/PPP1PPPP/RNBQKBNR',
      onChange: playLondon
    }
    Chessboard('myBoard', config)

//d

  } else if (Chessboard.objToFen(newPos) == 'rnbqkbnr/ppp1pppp/8/3p4/3P1B2/8/PPP1PPPP/RN1QKBNR') {
    var config = {
      orientation: 'white',
      draggable: true,
      position: 'r1bqkbnr/ppp1pppp/2n5/3p4/3P1B2/8/PPP1PPPP/RN1QKBNR',
      onChange: playLondon
    }
    Chessboard('myBoard', config)

  } else if (Chessboard.objToFen(newPos) == 'r1bqkbnr/ppp1pppp/2n5/3p4/3P1B2/4P3/PPP2PPP/RN1QKBNR') {

   londonCompleted();
  }
};


playLondon();


// if user finishes this function will fire

 async function londonCompleted() {


  const id = window.location.toString().split('/')[
    window.location.toString().split('/').length - 1
  ];
console.log(id);

  const res = await fetch('/puzzles/completed', {
    method: 'PUT',
    body: JSON.stringify({
		id: id,
        puzzleid: id
    }),
    headers: {
      'Content-Type': 'application/json'
    }
  });

  if (res.ok) {
     alert("Congratulations! Check Your Dashboard To View Your New Badge!")
  } else {
    alert(res.statusText);
  }
}















