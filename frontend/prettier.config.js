/**
 * @type {import("prettier").Config}
 */
const config = {
  trailingComma: "es5",
  tabWidth: 2,
  semi: true,
  singleQuote: false,
  endOfLine: "lf",
  useTabs: false,
  plugins: ["prettier-plugin-tailwindcss"],
};

export default config;
