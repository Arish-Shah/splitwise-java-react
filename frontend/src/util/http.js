const API_URL = import.meta.env.VITE_API_URL;

async function post(url, data, headers = {}) {
  const response = await fetch(API_URL + url, {
    method: "POST",
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json",
      ...headers,
    },
  });
  const json = await response.json();
  if (!response.ok) throw new Error(json.message);
  return json;
}

export const http = {
  post,
};
