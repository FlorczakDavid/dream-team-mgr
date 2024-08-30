//obligatoire
export const requiredMessage = (value) => `${value} is mandatory`;

// maxlength
export const maxLengthMessage = (value, length) =>
  `${value} must be no longer than ${length} characters`;