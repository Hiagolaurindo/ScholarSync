import { render, screen } from '@testing-library/react';
import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import '../src/main.jsx';
test('LoginPage renderiza campos e botao', () => {
  render(<BrowserRouter><div><input placeholder='email'/><input placeholder='password'/><button>Entrar</button></div></BrowserRouter>);
  expect(screen.getByPlaceholderText('email')).toBeTruthy();
  expect(screen.getByPlaceholderText('password')).toBeTruthy();
  expect(screen.getByText('Entrar')).toBeTruthy();
});
