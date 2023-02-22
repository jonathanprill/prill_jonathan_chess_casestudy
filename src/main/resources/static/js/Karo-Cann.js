//	<!-- chessboard.js v1.0.0 | (c) 2019 Chris Oakman | MIT License chessboardjs.com/license -->

var playKaroCann = function (APIconfigFiller, newPos) {

  var config = {
    orientation: 'black',
    draggable: true,
    position: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR',
    onChange: playKaroCann
  }
  board = Chessboard('myBoard', config)



  if (Chessboard.objToFen(newPos) == 'rnbqkbnr/pp1ppppp/2p5/8/4P3/8/PPPP1PPP/RNBQKBNR') {

    var config = {
      orientation: 'black',
      draggable: true,
      position: 'rnbqkbnr/pp1ppppp/2p5/8/3PP3/8/PPP2PPP/RNBQKBNR',
      onChange: playKaroCann
    }
    Chessboard('myBoard', config)



  } else if (Chessboard.objToFen(newPos) == 'rnbqkbnr/pp2pppp/2p5/3p4/3PP3/8/PPP2PPP/RNBQKBNR') {
    var config = {
      orientation: 'black',
      draggable: true,
      position: 'rnbqkbnr/pp2pppp/2p5/3P4/3P4/8/PPP2PPP/RNBQKBNR',
      onChange: playKaroCann
    }
    Chessboard('myBoard', config)

  } else if (Chessboard.objToFen(newPos) == 'rnbqkbnr/pp2pppp/8/3p4/3P4/8/PPP2PPP/RNBQKBNR') {

    caroKannCompleted();

  }
};


playKaroCann();


// if user finishes this function will fire

 async function caroKannCompleted() {


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
    document.location.reload();
  } else {
    alert(res.statusText);
  }
}




