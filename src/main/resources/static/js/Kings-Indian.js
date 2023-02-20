
var playKingsIndian = function (APIconfigFiller, newPos) {

  var config = {
    orientation: 'black',
    draggable: true,
    position: 'rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR',
    onChange: playKingsIndian
  }
  board = Chessboard('myBoard', config)



  if (Chessboard.objToFen(newPos) == 'rnbqkbnr/pppppp1p/6p1/8/4P3/8/PPPP1PPP/RNBQKBNR') {

    var config = {
      orientation: 'black',
      draggable: true,
      position: 'rnbqkbnr/pppppp1p/6p1/8/3PP3/8/PPP2PPP/RNBQKBNR',
      onChange: playKingsIndian
    }
    Chessboard('myBoard', config)



  } else if (Chessboard.objToFen(newPos) == 'rnbqk1nr/ppppppbp/6p1/8/3PP3/8/PPP2PPP/RNBQKBNR') {
    var config = {
      orientation: 'black',
      draggable: true,
      position: 'rnbqk1nr/ppppppbp/6p1/8/3PP3/5N2/PPP2PPP/RNBQKB1R',
      onChange: playKingsIndian
    }
    Chessboard('myBoard', config)

  } else if (Chessboard.objToFen(newPos) == 'rnbqk2r/ppppppbp/5np1/8/3PP3/5N2/PPP2PPP/RNBQKB1R') {

   kingsIndianCompleted();
  }
};


playKingsIndian();


// if user finishes this function will fire

 async function kingsIndianCompleted() {


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















