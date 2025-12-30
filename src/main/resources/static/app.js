(function () {
  const form = document.getElementById("helloForm");
  const output = document.getElementById("output");
  const nameInput = document.getElementById("name");

  async function callApi(name) {
    const url = name && name.trim()
      ? `/api/hello?name=${encodeURIComponent(name.trim())}`
      : "/api/hello";

    const res = await fetch(url, { headers: { "Accept": "application/json" } });
    if (!res.ok) {
      throw new Error(`HTTP ${res.status}`);
    }
    return res.json();
  }

  async function run(name) {
    output.textContent = "Chargement...";
    try {
      const data = await callApi(name);
      output.textContent = JSON.stringify(data, null, 2);
    } catch (e) {
      output.textContent = `Erreur: ${e.message}`;
    }
  }

  if (form) {
    form.addEventListener("submit", (e) => {
      e.preventDefault();
      run(nameInput.value);
    });
    run(nameInput.value);
  }
})();
