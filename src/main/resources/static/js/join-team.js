/**
 * This file will handle to ability for users to join a team while on the single-team page
 */
 
 async function joinTeamClickHandler(e) {
  e.preventDefault();

  const id = window.location.toString().split('/')[
    window.location.toString().split('/').length - 1
  ];
console.log(id);

  const res = await fetch('/teams/join', {
    method: 'PUT',
    body: JSON.stringify({
		id: id,
        teamid: id
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

document.querySelector('.team-join-btn').addEventListener('click', joinTeamClickHandler);