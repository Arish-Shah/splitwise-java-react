const API_URL = import.meta.env.VITE_API_URL;

async function post(url, data) {
  const response = await fetch(API_URL + url, {
    method: "POST",
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json",
    },
  });
  const json = await response.json();
  if (!response.ok) throw new Error(json.message);
  return json;
}

async function get(url) {
  const response = await fetch(API_URL + url, {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  });
  const json = await response.json();
  if (!response.ok) throw new Error(json.message);
  return json;
}

async function put(url) {
  const response = await fetch(API_URL + url, {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  });
  const json = await response.json();
  if (!response.ok) throw new Error(json.message);
  return json;
}

export const http = {
  get,
  post,
  put,
};
