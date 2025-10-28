const apiBase = "http://localhost:8080/api/cars";
const authBase = "http://localhost:8080/api/auth";

function toggleAuth(type) {
  document.getElementById("loginForm").classList.toggle("hidden", type === "register");
  document.getElementById("registerForm").classList.toggle("hidden", type === "login");
  clearAuthFields();
}

function clearAuthFields() {
  document.getElementById("loginUsername").value = "";
  document.getElementById("loginPassword").value = "";
  document.getElementById("regUsername").value = "";
  document.getElementById("regPassword").value = "";
}

function login() {
  const username = document.getElementById("loginUsername").value.trim();
  const password = document.getElementById("loginPassword").value.trim();

  if (!username || !password) {
    alert("Please fill in both fields.");
    return;
  }

  fetch(`${authBase}/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  })
    .then(res => {
      if (!res.ok) throw new Error("Login failed");
      return res.json();
    })
    .then(data => {
      localStorage.setItem("token", data.token);
      document.getElementById("authSection").classList.add("hidden");
      document.getElementById("appSection").classList.remove("hidden");
      fetchCars();
      clearAuthFields();
    })
    .catch(err => alert("Invalid credentials or server error."));
}

function register() {
  const username = document.getElementById("regUsername").value.trim();
  const password = document.getElementById("regPassword").value.trim();

  if (!username || !password) {
    alert("Please fill in both fields.");
    return;
  }

  fetch(`${authBase}/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  })
    .then(res => {
      if (!res.ok) throw new Error("Registration failed");
      alert("Registration successful! You can now log in.");
      toggleAuth("login");
    })
    .catch(() => alert("Registration failed. Username might already exist."));
}

function logout() {
  localStorage.removeItem("token");
  document.getElementById("appSection").classList.add("hidden");
  document.getElementById("authSection").classList.remove("hidden");
  toggleAuth("login");
  clearAuthFields();
}

function fetchCars() {
  fetch(apiBase)
    .then(res => {
      if (!res.ok) throw new Error("Failed to fetch cars");
      return res.json();
    })
    .then(cars => {
      const tbody = document.getElementById("carsTableBody");
      tbody.innerHTML = "";

      cars.forEach(car => {
        tbody.innerHTML += `
          <tr class="text-center">
            <td class="p-2 border">${car.id}</td>
            <td class="p-2 border">${car.licensePlateNumber}</td>
            <td class="p-2 border">${car.make}</td>
            <td class="p-2 border">${car.model}</td>
            <td class="p-2 border">${car.year}</td>
            <td class="p-2 border">${car.color}</td>
            <td class="p-2 border">${car.bodyType}</td>
            <td class="p-2 border">${car.engineType}</td>
            <td class="p-2 border">${car.transmission}</td>
            <td class="p-2 border space-x-2">
              <button 
                onclick="openEditModal(${car.id}, '${car.licensePlateNumber}', '${car.make}', '${car.model}', '${car.year}', '${car.color}', '${car.bodyType}', '${car.engineType}', '${car.transmission}')"
                class="bg-yellow-500 hover:bg-yellow-600 text-white px-3 py-1 rounded">
                Edit
              </button>
              <button 
                onclick="deleteCar(${car.id})" 
                class="bg-red-500 hover:bg-red-700 text-white px-3 py-1 rounded">
                Delete
              </button>
            </td>
          </tr>
        `;
      });
    })
    .catch(err => console.error("Error fetching cars:", err));
}

function openCreateModal() {
  document.getElementById("carForm").reset();
  document.getElementById("carId").value = "";
  document.getElementById("modalTitle").innerText = "Add Car";
  document.getElementById("carModal").classList.remove("hidden");
}

function openEditModal(id, licensePlateNumber, make, model, year, color, bodyType, engineType, transmission) {
  document.getElementById("carId").value = id;
  document.getElementById("licensePlateNumber").value = licensePlateNumber;
  document.getElementById("make").value = make;
  document.getElementById("model").value = model;
  document.getElementById("year").value = year;
  document.getElementById("color").value = color;
  document.getElementById("bodyType").value = bodyType;
  document.getElementById("engineType").value = engineType;
  document.getElementById("transmission").value = transmission;
  document.getElementById("modalTitle").innerText = "Edit Car";
  document.getElementById("carModal").classList.remove("hidden");
}

function closeModal() {
  document.getElementById("carModal").classList.add("hidden");
}

function saveCar(e) {
  e.preventDefault();

  const id = document.getElementById("carId").value;
  const licensePlateNumber = document.getElementById("licensePlateNumber").value;
  const make = document.getElementById("make").value;
  const model = document.getElementById("model").value;
  const year = document.getElementById("year").value;
  const color = document.getElementById("color").value;
  const bodyType = document.getElementById("bodyType").value;
  const engineType = document.getElementById("engineType").value;
  const transmission = document.getElementById("transmission").value;

  const car = { licensePlateNumber, make, model, year, color, bodyType, engineType, transmission };
  const method = id ? "PUT" : "POST";
  const url = id ? `${apiBase}/${id}` : apiBase;

  fetch(url, {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(car),
  })
    .then(res => {
      if (!res.ok) throw new Error("Failed to save car");
      return res.json();
    })
    .then(() => {
      closeModal();
      fetchCars();
    })
    .catch(err => console.error("Error saving car:", err));
}

function deleteCar(id) {
  if (!confirm("Delete this car?")) return;

  fetch(`${apiBase}/${id}`, { method: "DELETE" })
    .then(res => {
      if (!res.ok) throw new Error("Failed to delete car");
      fetchCars();
    })
    .catch(err => console.error("Error deleting car:", err));
}